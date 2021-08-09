import { Route } from '@angular/router';

import { AppMstComponent } from './app-mst.component';

export const appMstRoute: Route = {
  path: ':appId/app-mst',
  component: AppMstComponent,
  data: {
    pageTitle: 'Application',
  },
};
