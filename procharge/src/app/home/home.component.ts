import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  showCard: number = 1;

  constructor() { }

  ngOnInit() {
    this.rotateCards();
  }

  rotateCards() {
    setInterval(() => {
      this.showCard = this.showCard % 3 + 1;
    }, 7000); // Rotate every 5 seconds (5000 milliseconds)
  }

}
