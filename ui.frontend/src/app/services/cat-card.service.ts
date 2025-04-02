import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CatCardService {
  private readonly apiUrl = '/bin/catapi';

  constructor(private readonly http: HttpClient) {}

  getCatImages(count: number): Observable<any> {
    return this.http.get(`${this.apiUrl}?count=${count}`);
  }
}