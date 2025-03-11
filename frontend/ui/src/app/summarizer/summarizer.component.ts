import {Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-summarizer',
  imports: [],
  templateUrl: './summarizer.component.html',
  styleUrl: './summarizer.component.scss'
})
export class SummarizerComponent {
  text: string = '';
  summary: string = '';

  constructor(private Http: HttpClient) {

  }

  summarize(){
    this.Http.post<any>('http://localhost:8080/api/summarize', { text:this.text})
      .subscribe(response => {
        this.summary = response.summary
      });
  }
}
