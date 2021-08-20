import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { RouterModule } from '@angular/router';
import { AddMaterialComponent } from './add-material/add-material.component';
import { appMaterialState } from './app-material.route';
import { EditMaterialComponent } from './edit-material/edit-material.component';
import { ViewMaterialComponent } from './view-material/view-material.component';
import { AppMaterialListComponent } from './app-material-list/app-material-list.component';

@NgModule({
  imports: [SharedModule, RouterModule.forChild(appMaterialState)],
  declarations: [AddMaterialComponent, AppMaterialListComponent, EditMaterialComponent, ViewMaterialComponent],
})
export class AppMaterialModule {}
