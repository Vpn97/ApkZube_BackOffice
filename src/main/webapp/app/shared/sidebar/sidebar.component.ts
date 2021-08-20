import { Component, OnInit } from '@angular/core';
import { Account } from 'app/core/auth/account.model';
import { AccountService } from 'app/core/auth/account.service';
import { LoginService } from 'app/login/login.service';

export interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
}

export const ROUTES: RouteInfo[] = [
  { path: '/home', title: 'Dashboard', icon: 'nc-bank', class: '' },
  { path: '/app-mst/app-mst-list', title: 'Applications', icon: 'nc-app', class: '' },
  { path: '/maps', title: 'Maps', icon: 'nc-pin-3', class: '' },
  { path: '/notifications', title: 'Notifications', icon: 'nc-bell-55', class: '' },
  { path: '/apkzube/user', title: 'User Profile', icon: 'nc-single-02', class: '' },
  { path: '/table', title: 'Table List', icon: 'nc-tile-56', class: '' },
  { path: '/typography', title: 'Typography', icon: 'nc-caps-small', class: '' },
  { path: '/admin/docs', title: 'APIs', icon: 'nc-planet', class: 'active-pro' },
];

@Component({
  selector: 'sidebar-cmp',
  templateUrl: './sidebar.component.html',
})
export class SidebarComponent implements OnInit {
  menuItems?: any[];

  account: Account | null = null;

  constructor(private loginService: LoginService, private accountService: AccountService) {}

  ngOnInit(): void {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-condition
    this.menuItems = ROUTES.filter(menuItem => menuItem);
  }
}
