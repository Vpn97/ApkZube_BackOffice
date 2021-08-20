import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { appMstState } from './app-mst.route';
import { RouterModule } from '@angular/router';
import { AppMstListComponent } from './app-mst-list/app-mst-list.component';
import { AppMstDtlComponent } from './app-mst-dlt/app-mst-dtl.component';

@NgModule({
  imports: [SharedModule, RouterModule.forChild(appMstState)],
  declarations: [AppMstListComponent, AppMstDtlComponent],
})
export class AppMstModule {}
