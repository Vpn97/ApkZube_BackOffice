import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { AddMaterialComponent } from './add-material/add-material.component';
import { AppMaterialListComponent } from './app-material-list/app-material-list.component';
import { EditMaterialComponent } from './edit-material/edit-material.component';

export const appMaterialState: Routes = [
  {
    path: 'material',
    canActivate: [UserRouteAccessService],
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
        component: EditMaterialComponent,
        data: {
          pageTitle: 'View Material',
        },
      },
    ],
  },
];
