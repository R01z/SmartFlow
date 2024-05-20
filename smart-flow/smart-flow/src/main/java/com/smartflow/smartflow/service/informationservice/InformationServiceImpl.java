package com.smartflow.smartflow.service.informationservice;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smartflow.smartflow.dto.informationdto.InformationDTO;
import com.smartflow.smartflow.model.Information;
import com.smartflow.smartflow.model.Teams;
import com.smartflow.smartflow.model.TypeInformation;
import com.smartflow.smartflow.repository.InformationRepository;
import com.smartflow.smartflow.repository.TeamsRepository;
import com.smartflow.smartflow.repository.TypeInformationRepository;
import com.smartflow.smartflow.util.FileNameSanitizer;

@Service
public class InformationServiceImpl implements InformationService {

    @Autowired
    InformationRepository informationRepository;

    @Autowired
    TeamsRepository teamsRepository;

    @Autowired
    TypeInformationRepository typeInformationRepository;

    private static final String BASE_DIRECTORY = System.getProperty("java.io.tmpdir") + File.separator;

    @Override
    public Iterable<Information> findAll(Specification<Information> spec) {
        return informationRepository.findAll(spec);
    }

    @Override
    public String addInformation(InformationDTO informationDTO) {
        Information information = new Information();

        information.setName(informationDTO.getName());
        information.setDescription(informationDTO.getDescription());
        information.setLink(informationDTO.getLink());
        information.setTags(informationDTO.getTags());

        Teams team = teamsRepository.getReferenceById(informationDTO.getTeamId());
        information.setTeam(team);

        if (informationDTO.getInformationId() != null) {
            // Atualização de uma informação existente
            information = informationRepository.findById(informationDTO.getInformationId())
                    .orElseThrow(() -> new RuntimeException("Information not found"));
            information.setUploadDate(new Timestamp(System.currentTimeMillis())); // Atualiza a data de upload

        } else {
            // Criação de uma nova informação
            information.setUploadDate(new Timestamp(System.currentTimeMillis())); // Define a data de upload
        }

        MultipartFile file = informationDTO.getFile();
        if (file != null && !file.isEmpty()) {
            try {
                // Cria o diretório com base no teamId, se não existir
                String teamDirectoryPath = BASE_DIRECTORY + informationDTO.getTeamId() + File.separator;
                File teamDirectory = new File(teamDirectoryPath);
                if (!teamDirectory.exists()) {
                    teamDirectory.mkdirs(); // Cria o diretório e todos os diretórios pais, se necessário
                }

                // Definir nome com que arquivo será salvo
                String originalFileName = file.getOriginalFilename();
                String sanitizedFileName = FileNameSanitizer.sanitizeFileName(originalFileName);

                // Define o caminho de destino para salvar o arquivo dentro do diretório do time
                String filePath = teamDirectoryPath + sanitizedFileName;

                // Salva o arquivo no diretório do time
                File destFile = new File(filePath);
                file.transferTo(destFile);

                // Define o caminho do arquivo no objeto Information
                information.setFilePath(filePath);
            } catch (IOException e) {
                e.printStackTrace();
                // Trate o erro conforme necessário
            }
        }

        TypeInformation type = typeInformationRepository.getReferenceById(informationDTO.getTypeId());
        information.setTypeInformation(type);

        informationRepository.save(information);

        return "";
    }

    public boolean deleteInformationById(Integer id) {
        if (informationRepository.existsById(id)) {
            informationRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Information findInformationById(Integer id) {
        Information information = new Information();
        if (informationRepository.existsById(id)) {
            information = informationRepository.getReferenceById(id);
            return information;
        } else {
            return null;
        }
    }

}
