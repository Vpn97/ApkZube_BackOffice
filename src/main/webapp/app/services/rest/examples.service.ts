import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { Observable } from 'rxjs';
import { ProgramCategoryDTO } from '../model/program-category-dto.model';
import { ProgramMstDTO } from '../model/program-mst-dto.model';

@Injectable({
  providedIn: 'root',
})
export class ExamplesService {
  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  categories(appId: number): Observable<ProgramCategoryDTO[]> {
    return this.http.get<ProgramCategoryDTO[]>(this.applicationConfigService.getEndpointFor('api/examples/categories'), {
      params: { appId },
    });
  }

  category(catId: number): Observable<ProgramCategoryDTO> {
    return this.http.get<ProgramCategoryDTO>(this.applicationConfigService.getEndpointFor('api/examples/category'), {
      params: { catId },
    });
  }

  addCategory(formData: FormData): Observable<any> {
    return this.http.post(this.applicationConfigService.getEndpointFor('api/examples/addCategory'), formData);
  }

  updateCategory(formData: FormData): Observable<any> {
    return this.http.post(this.applicationConfigService.getEndpointFor('api/examples/updateCategory'), formData);
  }

  getExamplesByCategory(catId: number): Observable<ProgramMstDTO[]> {
    return this.http.get<ProgramMstDTO[]>(this.applicationConfigService.getEndpointFor('api/examples/getExamplesByCategory'), {
      params: { catId },
    });
  }

  programById(programId: number): Observable<ProgramMstDTO> {
    return this.http.get<ProgramMstDTO>(this.applicationConfigService.getEndpointFor('api/examples/programById'), {
      params: { programId },
    });
  }

  saveProgram(formData: ProgramMstDTO): Observable<any> {
    return this.http.post(this.applicationConfigService.getEndpointFor('api/examples/saveProgram'), formData);
  }
}
