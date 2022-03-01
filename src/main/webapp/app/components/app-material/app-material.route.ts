import { Routes } from '@angular/router';
import { Authority } from 'app/config/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { AddMaterialComponent } from './add-material/add-material.component';
import { AppMaterialListComponent } from './app-material-list/app-material-list.component';
import { EditMaterialComponent } from './edit-material/edit-material.component';
import { ViewMaterialComponent } from './view-material/view-material.component';

export const appMaterialState: Routes = [
  {
    path: 'material',
    canActivate: [UserRouteAccessService],
    data: {
      authorities: [Authority.ADMIN],
    },
    children: [
      {
        path: 'list/:appId',
        component: AppMaterialListComponent,
        data: {
          pageTitle: 'Application Material List',
        },
      },

      {
        path: 'add/:appId',
        component: AddMaterialComponent,
        data: {
          pageTitle: 'Add Material',
        },
      },

      {
        path: 'edit/:matId',
        component: EditMaterialComponent,
        data: {
          pageTitle: 'Edit Material',
        },
      },

      {
        path: 'view/:matId',
        component: ViewMaterialComponent,
        data: {
          pageTitle: 'View Material',
        },
      },
    ],
  },
];
