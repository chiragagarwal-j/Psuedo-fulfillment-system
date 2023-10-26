import { Component, OnInit } from '@angular/core';
import { NewSimService } from '../services/new-sim.service';
import * as html2pdf from 'html2pdf.js'

@Component({
  selector: 'app-order-status',
  templateUrl: './order-status.component.html',
  styleUrls: ['./order-status.component.css']
})
export class OrderStatusComponent implements OnInit {

  allOrderDetails: any;
  constructor(private newSimService: NewSimService) { }

  ngOnInit() {
    this.getOrderDetails();
  }

  getOrderDetails() {
    this.newSimService.fetchOrderDetails().subscribe((data) => {
      this.allOrderDetails = data;
    });
  }


  generatePDF() {
    const element = document.getElementById('invoice');
  
    if (element) {
      const opt = {
        margin: 10,
        filename: 'invoice.pdf',
        image: { type: 'jpeg', quality: 0.98 },
        html2canvas: { scale: 2 },
        jsPDF: { unit: 'mm', format: 'a4', orientation: 'portrait' },
      };
  
      html2pdf()
        .from(element)
        .set(opt)
        .outputPdf()
        .then((pdf) => {
          pdf.save('invoice.pdf');
        });
    }
  }
  

}