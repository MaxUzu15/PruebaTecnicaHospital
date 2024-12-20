import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-views',
  standalone: false,
  templateUrl: './views.component.html',
  styleUrl: './views.component.css'
})
export class ViewsComponent implements OnInit{
  constructor(private router: Router){
    
  }
  ngOnInit(): void {
      this.router.navigate(['/menu'])
  }
}
