import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AppMst } from 'app/services/model/app-mst.model';
import { TutorialCategoryMst } from 'app/services/model/tutorial-cat-mst.model';
import { TutorialMstDTO } from 'app/services/model/tutorial-mst-dto.model';
import { AppMstService } from 'app/services/rest/app-mst.service';
import { CategoryService } from 'app/services/rest/category.service';
import { TutorialService } from 'app/services/rest/tutorial.service';
import { Editor, Toolbar } from 'ngx-editor';

@Component({
  selector: 'jhi-save-tutorial-mst',
  templateUrl: './save-tutorial-mst.component.html',
  styleUrls: ['./save-tutorial-mst.component.scss'],
})
export class SaveTutorialMstComponent implements OnInit, OnDestroy {
  appId!: number;
  catId!: number;
  mstId!: number;
  isUpdate = false;
  appMst!: AppMst;
  tutMst!: TutorialMstDTO;
  catList: TutorialCategoryMst[] | null = null;

  editor: Editor = new Editor();
  toolbar: Toolbar = [
    ['bold', 'italic'],
    ['underline', 'strike'],
    ['code', 'blockquote'],
    ['ordered_list', 'bullet_list'],
    [{ heading: ['h1', 'h2', 'h3', 'h4', 'h5', 'h6'] }],
    ['link', 'image'],
    ['text_color', 'background_color'],
    ['align_left', 'align_center', 'align_right', 'align_justify'],
  ];

  isSaving = false;

  imgFileName = 'Choose backgroid image';
  imgFileSrc: string | ArrayBuffer | null | undefined;

  tutorialForm = this.fb.group({
    title: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(100)]],
    description: ['', [Validators.required, Validators.minLength(8)]],
    active: [true, [Validators.required]],
    tutCatMstId: ['', [Validators.required]],
    imgFile: [''],
    imgURL: [''],
  });

  constructor(
    private tutorialService: TutorialService,
    private categoryService: CategoryService,
    private appMstService: AppMstService,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.editor = new Editor();

    this.route.queryParams.subscribe(data => {
      this.appId = Number(data.appId);

      if (data.catId) {
        this.catId = Number(data.catId);
      }

      if (data.mstId) {
        this.mstId = Number(data.mstId);
        this.tutorialService.getTutorialMstById(this.mstId).subscribe(mst => {
          this.tutMst = mst;
          this.tutorialForm.get('title')?.setValue(this.tutMst.title);
          this.tutorialForm.get('description')?.setValue(this.tutMst.description);
          this.tutorialForm.get('tutCatMstId')?.setValue(this.tutMst.tutCatMstId);
          this.tutorialForm.get('active')?.setValue(this.tutMst.active);
          this.tutorialForm.get('imgURL')?.setValue(this.tutMst.imgURL);
          this.imgFileSrc = this.tutMst.imgURL;
        });
      }

      this.appMstService.findAppMstById(this.appId).subscribe(app => {
        this.appMst = app as AppMst;
      });

      this.categoryService.getAllTutCategoryByAppId(this.appId).subscribe(catMstList => {
        this.catList = catMstList;
        if (this.catId) {
          this.tutorialForm.get('tutCatMstId')?.setValue(this.catId);
        }
      });
      this.isUpdate = data.isUpdate;
    });
  }

  ngOnDestroy(): void {
    this.editor.destroy();
  }

  onImgFileChange(event: any): void {
    if (event.target.files.length > 0) {
      const file: File = event.target.files[0];
      this.tutorialForm.patchValue({
        imgFile: file,
      });

      this.imgFileName = file.name;
      const reader = new FileReader();
      reader.onload = () => (this.imgFileSrc = reader.result);

      reader.readAsDataURL(file);
    } else {
      this.imgFileName = 'Choose backgroid image';
      this.imgFileSrc = '';
      this.tutorialForm.patchValue({
        imgFile: null,
      });
    }
  }

  save(): void {
    if (this.tutorialForm.valid) {
      this.isSaving = true;
      const formData: FormData = new FormData();

      formData.append('appId', this.tutorialForm.get('appId')?.value);
      if (this.mstId) {
        formData.append('tutMstId', this.mstId.toString());
      }
      formData.append('tutCatMstId', this.tutorialForm.get('tutCatMstId')?.value);
      formData.append('title', this.tutorialForm.get('title')?.value);
      formData.append('description', this.tutorialForm.get('description')?.value);
      formData.append('active', this.tutorialForm.get('active')?.value);

      if (this.tutorialForm.get('imgFile')?.value) {
        formData.append('imgFile', this.tutorialForm.get('imgFile')?.value);
      }

      this.tutorialService.saveTutorialMst(formData).subscribe(
        () => {
          this.previousState();
        },
        () => (this.isSaving = false)
      );
    }
  }

  previousState(): void {
    window.history.back();
  }

  clearForm(): void {
    this.tutorialForm.reset();

    if (this.isUpdate) {
      this.tutorialForm.get('title')?.setValue(this.tutMst.title);
      this.tutorialForm.get('description')?.setValue(this.tutMst.description);
      this.tutorialForm.get('tutCatMstId')?.setValue(this.tutMst.tutCatMstId);
      this.tutorialForm.get('active')?.setValue(this.tutMst.active);
      this.tutorialForm.get('imgURL')?.setValue(this.tutMst.imgURL);
      this.imgFileSrc = this.tutMst.imgURL;
    } else {
      this.tutorialForm.get('appId')?.setValue(this.appId);

      this.tutorialForm.get('title')?.setValue('');
      this.tutorialForm.get('description')?.setValue('');
      this.tutorialForm.get('active')?.setValue(true);
      this.tutorialForm.get('iconURL')?.setValue('');
      this.tutorialForm.get('iconFile')?.setValue('');
      this.imgFileName = 'Choose backgroid image';
      this.imgFileSrc = null;
    }
  }
}
