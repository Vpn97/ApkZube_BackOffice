import { Routes } from '@angular/router';
import { Authority } from 'app/config/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { AddCategoryComponent } from './category/add-category/add-category.component';
import { EditCategoryComponent } from './category/edit-category/edit-category.component';
import { ExampleCategoryComponent } from './category/example-category/example-category.component';
import { ViewCategoryComponent } from './category/view-category/view-category.component';
import { AddExampleComponent } from './example/add-example/add-example.component';
import { EditExampleComponent } from './example/edit-example/edit-example.component';
import { ExampleListComponent } from './example/example-list/example-list.component';
import { ViewExampleComponent } from './example/view-example/view-example.component';

export const examplesState: Routes = [
  {
    path: 'code-examples',
    canActivate: [UserRouteAccessService],
    data: {
      authorities: [Authority.ADMIN],
    },
    children: [
      {
        path: 'cat/:appId',
        component: ExampleCategoryComponent,
        data: {
          pageTitle: 'Category',
        },
      },

      {
        path: 'cat/view/:catId',
        component: ViewCategoryComponent,
        data: {
          pageTitle: 'View Category',
        },
      },

      {
        path: 'cat/edit/:catId',
        component: EditCategoryComponent,
        data: {
          pageTitle: 'Edit Category',
        },
      },

      {
        path: 'cat/add/:appId',
        component: AddCategoryComponent,
        data: {
          pageTitle: 'View Category',
        },
      },

      {
        path: 'cat/example-list/:appId/:catId',
        component: ExampleListComponent,
        data: {
          pageTitle: 'Example List',
        },
      },

      {
        path: 'cat/add-example/:catId/:appId/:programId',
        component: AddExampleComponent,
        data: {
          pageTitle: 'Add Example',
        },
      },

      {
        path: 'cat/edit-example/:codeId',
        component: EditExampleComponent,
        data: {
          pageTitle: 'Edit Example',
        },
      },

      {
        path: 'cat/view-example/:codeId/:catId/:appId',
        component: ViewExampleComponent,
        data: {
          pageTitle: 'View Example',
        },
      },
    ],
  },
];
