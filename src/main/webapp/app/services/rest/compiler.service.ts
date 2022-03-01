import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { Observable } from 'rxjs';
import { CompilerDTO, ICompilerDTO } from '../model/compiler-dto.model';

@Injectable({
  providedIn: 'root',
})
export class CompilerService {
  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  python3(compilerDTO: CompilerDTO): Observable<ICompilerDTO> {
    return this.http.post<ICompilerDTO>(this.applicationConfigService.getEndpointFor('api/compiler/python3'), compilerDTO);
  }
}
