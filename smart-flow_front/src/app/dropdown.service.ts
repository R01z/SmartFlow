import { Injectable } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DropdownService {
  private isOpenSubject = new Subject<boolean>();
  isOpen$ = this.isOpenSubject.asObservable();

  constructor(private router: Router) {
    // Assim que a rota mudar, feche o dropdown
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        console.log('Rota alterada, fechando dropdown');
        this.closeDropdown();
      }
    });
  }

  openDropdown() {
    this.isOpenSubject.next(true);
  }

  closeDropdown() {
    this.isOpenSubject.next(false);
  }
}
