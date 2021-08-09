import { Route } from '@angular/router';

import { AppMstListComponent } from './app-mst-list.component';

export const appMstListRoute: Route = {
  path: 'app-mst-list',
  component: AppMstListComponent,
  data: {
    pageTitle: 'Application List',
  },
};
