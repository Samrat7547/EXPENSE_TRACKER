import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MonthNavigation } from '../models/models';
import { BehaviorSubject, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TableDatasourceService {

  constructor(private http: HttpClient) { }
  monthNavigationObservable = new Subject<MonthNavigation[]>();
  // monthNavigationSubject= new BehaviorSubject<MonthNavigation[]>([]);
  // monthNavigationObservable= this.monthNavigationSubject.asObservable();
  monthNavigationSelectedObservable = new Subject<MonthNavigation>();
  // monthNavigationSelectedSubject= new BehaviorSubject<MonthNavigation>({
  //   "monthNumber":'07',
  //   "monthYear":'2023'
  // });
  // monthNavigationSelectedObservable= this.monthNavigationSelectedSubject.asObservable();

  // setMonthNavigations(monthsNav:any){
  //   this.monthNavigationSubject.next(monthsNav);

  // }
  // setMonthNavigation(monthsNav:any){
  //   this.monthNavigationSelectedSubject.next(monthsNav);
  // }

  previousSavingsObservable = new Subject<{
    monthYear: string;
    monthNumber: string;
    sum: string;
  }>();
  currentSavingsRequestObservable = new Subject<{
    monthYear: string;
    monthNumber: string;
  }>();

  getMonthList() {
    return this.http.get<any>(
      'http://localhost:8090/expense/all/distinct'
    );
  }

  postTableRow(monthDataForBackEnd: any) {
    return this.http.post(
      'http://localhost:8090/expense/data',
      monthDataForBackEnd,
      { responseType: 'text' }
    );
  }
  getTableRows(monthYear: string, monthNumber: string, tableName: string) {
    let parameters = new HttpParams();
    parameters = parameters.append('monthYear', monthYear);
    parameters = parameters.append('monthNumber', monthNumber);
    parameters = parameters.append('tableName', tableName);
    return this.http.get<any>(
      `http://localhost:8090/expense/table/${tableName}/${monthYear}/${monthNumber}`,
      {
        params: parameters,
      }
    );
  }
  deleteTableRow(rowId: number) {
    return this.http.delete(
      'http://localhost:8090/expense/delete/' + rowId,
      {
        responseType: 'text',
      }
    );
  }
}
