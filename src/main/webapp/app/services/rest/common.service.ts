import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { Observable } from 'rxjs';
import { ActionCodeMst } from '../model/action-code-mst.model';

@Injectable({
  providedIn: 'root',
})
export class CommonService {
  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  fetchActionCodes(): Observable<ActionCodeMst[]> {
    return this.http.get<ActionCodeMst[]>(this.applicationConfigService.getEndpointFor('api/common/fetchActionCodes'));
  }
}
