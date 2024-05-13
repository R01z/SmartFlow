import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMuralMessageComponent } from './add-mural-message.component';

describe('AddMuralMessageComponent', () => {
  let component: AddMuralMessageComponent;
  let fixture: ComponentFixture<AddMuralMessageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddMuralMessageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddMuralMessageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
