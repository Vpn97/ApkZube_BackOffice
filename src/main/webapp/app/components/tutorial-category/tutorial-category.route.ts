import { Routes } from '@angular/router';
import { Authority } from 'app/config/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { AddCategoryComponent } from './add-category/add-category.component';
import { EditCategoryComponent } from './edit-category/edit-category.component';
import { ViewCategoryListComponent } from './view-category-list/view-category-list.component';
import { ViewCategoryComponent } from './view-category/view-category.component';

export const tutorialCatState: Routes = [
  {
    path: 'tut-cat',
    canActivate: [UserRouteAccessService],
    data: {
      authorities: [Authority.ADMIN],
    },
    children: [
      {
        path: 'view-category-list/:appId',
        component: ViewCategoryListComponent,
        data: {
          pageTitle: 'Category List',
        },
      },

      {
        path: 'edit-category/:catId',
        component: EditCategoryComponent,
        data: {
          pageTitle: 'Edit Category',
        },
      },
      {
        path: 'view-category/:catId',
        component: ViewCategoryComponent,
        data: {
          pageTitle: 'View Category',
        },
      },
      {
        path: 'add-category/:appId',
        component: AddCategoryComponent,
        data: {
          pageTitle: 'Add Category',
        },
      },
    ],
  },
];
