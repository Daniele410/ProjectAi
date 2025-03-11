import {Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-summarizer',
  templateUrl: './summarizer.component.html',
  styleUrls: ['./summarizer.component.scss'],
  imports: [
    FormsModule,
    NgIf
  ],
  standalone: true
})
export class SummarizerComponent {

  text: string = '';
  summary: string = '';
  loading: boolean = false;
  error: string | null = null;

  constructor(readonly http: HttpClient) {
  }

  summarize() {
    this.loading = true;
    this.error = null;
    this.summary = '';

    this.http.post<{ summary: string }>('http://localhost:8080/summarize', {text: this.text})
      .subscribe({
        next: (response) => {
          console.log("Response:", response);
          this.summary = response.summary;
          this.loading = false;
        },
        error: (error) => {
          console.error("Errore HTTP:", error);
          this.error = 'Errore nel generare il riassunto';
          this.loading = false;
        }
      });
  }
}
