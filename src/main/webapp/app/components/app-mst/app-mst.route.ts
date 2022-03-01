import { Routes } from '@angular/router';
import { Authority } from 'app/config/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { AppMstDtlComponent } from './app-mst-dlt/app-mst-dtl.component';
import { AppMstListComponent } from './app-mst-list/app-mst-list.component';

export const appMstState: Routes = [
  {
    path: 'app-mst',
    canActivate: [UserRouteAccessService],
    data: {
      authorities: [Authority.ADMIN],
    },
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
