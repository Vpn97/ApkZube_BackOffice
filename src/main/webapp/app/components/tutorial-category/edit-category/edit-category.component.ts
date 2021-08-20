import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AppMst } from 'app/services/model/app-mst.model';
import { TutorialCategoryMstDTO } from 'app/services/model/tut-cat-mst-dto.model';
import { TutorialCategoryMst } from 'app/services/model/tutorial-cat-mst.model';
import { TutorialCategoryType } from 'app/services/model/tutorial-category-type.model';
import { AppMstService } from 'app/services/rest/app-mst.service';
import { CategoryService } from 'app/services/rest/category.service';

@Component({
  selector: 'jhi-edit-category',
  templateUrl: './edit-category.component.html',
  styleUrls: ['./edit-category.component.scss'],
})
export class EditCategoryComponent implements OnInit {
  appId: number | undefined;
  appMst: AppMst | undefined;
  isSaving = false;
  imgFileName = 'Choose backgroid image';
  iconFileName = 'Choose icon';
  catMst: TutorialCategoryMstDTO | undefined;

  iconFileSrc: string | ArrayBuffer | null | undefined;
  imgFileSrc: string | ArrayBuffer | null | undefined;

  tutCatTypes: TutorialCategoryType[] | null = null;

  catForm = this.fb.group({
    categoryName: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(50)]],
    categoryDesc: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(200)]],
    catType: ['', [Validators.required]],
    active: [true, [Validators.required]],
    appId: ['', [Validators.required]],
    catId: ['', [Validators.required]],

    imgURL: [''],
    iconURL: [''],
    iconFile: [null],
    imgFile: [null],
  });

  constructor(
    private categoryService: CategoryService,
    private appMstService: AppMstService,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    const catId = this.route.snapshot.params['catId'];
    if (catId) {
      this.categoryService.getTutCategoryById(catId).subscribe(data => {
        this.catMst = data;
        this.catForm.get('catId')?.setValue(catId);
        this.appMst = this.catMst.appMst;
        this.catForm.get('appId')?.setValue(this.appMst.appId);
        this.appId = this.appMst.appId;
        this.loadCategoryTypes();

        this.clearForm();
      });
    }
  }

  loadCategoryTypes(): void {
    this.categoryService.fetchTutCatTypes().subscribe(data => {
      this.tutCatTypes = data as TutorialCategoryType[];
      this.catForm.get('catType')?.setValue(this.catMst?.catType);
    });
  }

  onIconFileChange(event: any): void {
    if (event.target.files.length > 0) {
      const file: File = event.target.files[0];
      this.catForm.patchValue({
        iconFile: file,
      });
      this.iconFileName = file.name;

      const reader = new FileReader();
      reader.onload = () => (this.iconFileSrc = reader.result);

      reader.readAsDataURL(file);
    } else {
      this.iconFileName = 'Choose icon';
      this.catForm.patchValue({
        iconFile: null,
      });
      this.iconFileSrc = '';
    }
  }

  onImgFileChange(event: any): void {
    if (event.target.files.length > 0) {
      const file: File = event.target.files[0];
      this.catForm.patchValue({
        imgFile: file,
      });

      this.imgFileName = file.name;
      const reader = new FileReader();
      reader.onload = () => (this.imgFileSrc = reader.result);

      reader.readAsDataURL(file);
    } else {
      this.imgFileName = 'Choose backgroid image';
      this.imgFileSrc = '';
      this.catForm.patchValue({
        imgFile: null,
      });
    }
  }

  previousState(): void {
    // alert(this.catForm);
    window.history.back();
  }

  save(): void {
    if (this.catForm.valid) {
      this.isSaving = true;
      const formData: FormData = new FormData();

      formData.append('tutCatMstId', this.catForm.get('catId')?.value);

      formData.append('appId', this.catForm.get('appId')?.value);
      formData.append('categoryName', this.catForm.get('categoryName')?.value);
      formData.append('categoryDesc', this.catForm.get('categoryDesc')?.value);
      formData.append('catType', this.catForm.get('catType')?.value);
      formData.append('active', this.catForm.get('active')?.value);

      if (this.catForm.get('imgFile')?.value) {
        formData.append('imgFile', this.catForm.get('imgFile')?.value);
      }
      if (this.catForm.get('iconFile')?.value) {
        formData.append('iconFile', this.catForm.get('iconFile')?.value);
      }
      this.categoryService.updateTutorialCategory(formData).subscribe(
        () => {
          this.previousState();
        },
        () => (this.isSaving = false)
      );
    }
  }

  clearForm(): void {
    this.catForm.get('appId')?.setValue(this.appId);
    this.catForm.get('catId')?.setValue(this.catMst?.tutCatMstId);

    this.catForm.get('categoryName')?.setValue(this.catMst?.categoryName);
    this.catForm.get('categoryDesc')?.setValue(this.catMst?.categoryDesc);
    this.catForm.get('active')?.setValue(this.catMst?.active);
    this.catForm.get('catType')?.setValue(this.catMst?.catType);

    this.catForm.get('imgURL')?.setValue(this.catMst?.imgURL);

    this.catForm.get('iconURL')?.setValue(this.catMst?.iconURL);

    this.catForm.get('iconFile')?.setValue(null);
    this.catForm.get('imgFile')?.setValue(null);
    this.imgFileName = 'Choose backgroid image';
    this.iconFileName = 'Choos icon';

    this.iconFileSrc = this.catMst?.iconURL;
    this.imgFileSrc = this.catMst?.imgURL;
  }
}
