import { Routes } from '@angular/router';
import { Authority } from 'app/config/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TutorialDtlComponent } from './tutorial-dtls/tutorial-dtl.component';
import { TutorialListComponent } from './tutorial-list/tutorial-list.component';
import { SaveTutorialMstComponent } from './tutorial-mst/save-tutorial-mst/save-tutorial-mst.component';
import { ViewTutorialMstComponent } from './tutorial-mst/view-tutorial-mst/view-tutorial-mst.component';

export const tutorialState: Routes = [
  {
    path: '',
    canActivate: [UserRouteAccessService],
    data: {
      authorities: [Authority.ADMIN],
    },
    children: [
      {
        path: 'list/:appId',
        component: TutorialListComponent,
        data: {
          pageTitle: 'Tutorial List',
        },
      },

      {
        path: 'view-tut-mst/:appId/:mstId',
        component: ViewTutorialMstComponent,
        data: {
          pageTitle: 'View Tutorial',
        },
      },

      {
        path: 'save-tut-mst',
        component: SaveTutorialMstComponent,
        data: {
          pageTitle: 'View Tutorial',
        },
      },

      {
        path: 'tut-dtls/:appId/:mstId',
        component: TutorialDtlComponent,
        data: {
          pageTitle: 'Details of Tutorial',
        },
      },
    ],
  },
];
