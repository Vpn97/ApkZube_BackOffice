import { Routes } from '@angular/router';
import { appMstListRoute } from './app-mst-list/app-mst-list.route';
import { appMstRoute } from './app-mst/app-mst.route';

const DASHBOARD_ROUTES = [appMstListRoute, appMstRoute];

export const dashboardState: Routes = [
  {
    path: '',
    children: DASHBOARD_ROUTES,
  },
];
