import {Component} from '@angular/core';
import {SummarizerComponent} from './summarizer/summarizer.component';

@Component({
  selector: 'app-root',
  template: '<app-summarizer></app-summarizer>',
  standalone: true,
  imports: [SummarizerComponent]
})
export class AppComponent {}
