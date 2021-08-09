import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { dashboardState } from './dashboard.route';
import { RouterModule } from '@angular/router';
import { AppMstListComponent } from './app-mst-list/app-mst-list.component';
import { AppMstComponent } from './app-mst/app-mst.component';

@NgModule({
  imports: [SharedModule, RouterModule.forChild(dashboardState)],
  declarations: [AppMstListComponent, AppMstComponent],
})
export class DashboardModule {}
