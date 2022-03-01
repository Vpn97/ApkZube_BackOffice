import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { Observable } from 'rxjs';
import { TutorialCategoryMstDTO } from '../model/tut-cat-mst-dto.model';
import { TutorialMstDTO } from '../model/tutorial-mst-dto.model';

@Injectable({
  providedIn: 'root',
})
export class TutorialService {
  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  categoryWithTutorialList(appId: number): Observable<TutorialCategoryMstDTO[]> {
    return this.http.get<TutorialCategoryMstDTO[]>(this.applicationConfigService.getEndpointFor('api/tutorial/categoryWithTutorialList'), {
      params: { appId },
    });
  }

  getTutorialMstById(mstId: number): Observable<TutorialMstDTO> {
    return this.http.get<TutorialMstDTO>(this.applicationConfigService.getEndpointFor('api/tutorial/getTutorialMstById'), {
      params: { mstId },
    });
  }

  saveTutorialMst(formData: FormData): Observable<any> {
    return this.http.post(this.applicationConfigService.getEndpointFor('api/tutorial/saveTutorialMst'), formData);
  }
}
