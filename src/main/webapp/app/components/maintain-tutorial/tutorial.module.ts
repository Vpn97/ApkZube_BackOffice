import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { RouterModule } from '@angular/router';
import { AgGridModule } from 'ag-grid-angular';
import { CodemirrorModule } from '@ctrl/ngx-codemirror';
import { MonacoEditorModule } from 'ngx-monaco-editor';
import { tutorialState } from './tutorial.route';
import { TutorialListComponent } from './tutorial-list/tutorial-list.component';
import { ViewTutorialMstComponent } from './tutorial-mst/view-tutorial-mst/view-tutorial-mst.component';
import { SaveTutorialMstComponent } from './tutorial-mst/save-tutorial-mst/save-tutorial-mst.component';
import { NgxEditorModule } from 'ngx-editor';
import { TutorialDtlComponent } from './tutorial-dtls/tutorial-dtl.component';
@NgModule({
  imports: [
    SharedModule,
    CodemirrorModule,
    MonacoEditorModule,
    NgxEditorModule.forRoot({
      locals: {
        // menu
        bold: 'Bold',
        italic: 'Italic',
        code: 'Code',
        blockquote: 'Blockquote',
        underline: 'Underline',
        strike: 'Strike',
        bullet_list: 'Bullet List',
        ordered_list: 'Ordered List',
        heading: 'Heading',
        h1: 'Header 1',
        h2: 'Header 2',
        h3: 'Header 3',
        h4: 'Header 4',
        h5: 'Header 5',
        h6: 'Header 6',
        align_left: 'Left Align',
        align_center: 'Center Align',
        align_right: 'Right Align',
        align_justify: 'Justify',
        text_color: 'Text Color',
        background_color: 'Background Color',

        // popups, forms, others...
        url: 'URL',
        text: 'Text',
        openInNewTab: 'Open in new tab',
        insert: 'Insert',
        altText: 'Alt Text',
        title: 'Title',
        remove: 'Remove',
      },
    }),
    RouterModule.forChild(tutorialState),
    AgGridModule.withComponents([]),
  ],
  declarations: [TutorialListComponent, ViewTutorialMstComponent, SaveTutorialMstComponent, TutorialDtlComponent],
})
export class TutorialModule {}
