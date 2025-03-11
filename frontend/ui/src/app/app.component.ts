import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  imports: [
    FormsModule
  ],
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  text: string = '';
  summary: string = '';

  constructor(private http: HttpClient) {}

  summarize() {
    this.http.post<any>('http://localhost:8080/summarize', {text: this.text})
      .subscribe(response => {
        this.summary = response;
      }, error => {
        console.error(error);
        this.summary = 'Errore nel generare il riassunto';
      });
  }
}
