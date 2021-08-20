import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { RouterModule } from '@angular/router';
import { AddCategoryComponent } from './add-category/add-category.component';
import { EditCategoryComponent } from './edit-category/edit-category.component';
import { ViewCategoryComponent } from './view-category/view-category.component';
import { ViewCategoryListComponent } from './view-category-list/view-category-list.component';
import { tutorialCatState } from './tutorial-category.route';
@NgModule({
  imports: [SharedModule, RouterModule.forChild(tutorialCatState)],
  declarations: [ViewCategoryListComponent, EditCategoryComponent, EditCategoryComponent, ViewCategoryComponent, AddCategoryComponent],
})
export class TutCategoryModule {}
