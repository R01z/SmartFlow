const express = require('express');
const path = require('path');

const app = express();

// Servir os arquivos estáticos do diretório correto
app.use(express.static(__dirname + '/dist/smart-flow_front/browser'));

app.get('/*', function(req, res) {
  res.sendFile(path.join(__dirname + '/dist/smart-flow_front/browser/index.html'));
});

app.listen(process.env.PORT || 8080, () => {
  console.log(`Server running on port ${process.env.PORT || 8080}`);
});
