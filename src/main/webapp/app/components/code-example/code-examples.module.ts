import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { RouterModule } from '@angular/router';
import { examplesState } from './code-examples.route';
import { ExampleListComponent } from './example/example-list/example-list.component';
import { EditExampleComponent } from './example/edit-example/edit-example.component';
import { AddExampleComponent } from './example/add-example/add-example.component';
import { ViewExampleComponent } from './example/view-example/view-example.component';
import { ExampleCategoryComponent } from './category/example-category/example-category.component';
import { EditCategoryComponent } from './category/edit-category/edit-category.component';
import { AddCategoryComponent } from './category/add-category/add-category.component';
import { ViewCategoryComponent } from './category/view-category/view-category.component';
import { AgGridModule } from 'ag-grid-angular';
import { CodemirrorModule } from '@ctrl/ngx-codemirror';
import { MonacoEditorModule } from 'ngx-monaco-editor';
@NgModule({
  imports: [SharedModule, CodemirrorModule, MonacoEditorModule, RouterModule.forChild(examplesState), AgGridModule.withComponents([])],
  declarations: [
    ExampleListComponent,
    EditExampleComponent,
    AddExampleComponent,
    ViewExampleComponent,
    ExampleCategoryComponent,
    EditCategoryComponent,
    AddCategoryComponent,
    ViewCategoryComponent,
  ],
})
export class CodeExamplesModule {}
