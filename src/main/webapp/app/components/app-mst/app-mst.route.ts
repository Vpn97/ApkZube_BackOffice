import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { AppMstDtlComponent } from './app-mst-dlt/app-mst-dtl.component';
import { AppMstListComponent } from './app-mst-list/app-mst-list.component';

export const appMstState: Routes = [
  {
    path: 'app-mst',
    canActivate: [UserRouteAccessService],
    children: [
      {
        path: 'app-mst-dtl/:appId',
        component: AppMstDtlComponent,
        data: {
          pageTitle: 'Application Details',
        },
      },

      {
        path: 'app-mst-list',
        component: AppMstListComponent,
        data: {
          pageTitle: 'Application List',
        },
      },
    ],
  },
];
