import { TestBed, async } from '@angular/core/testing';

import { AppComponent } from './app.component';

describe('AppComponent', () => {
  beforeEach(async(() => {
    console.info("\n\nbeforeEach");
    TestBed.configureTestingModule({
      declarations: [
        AppComponent
      ],
    }).overrideComponent(AppComponent, {
        set: { styleUrls: [] }
    }).compileComponents();
  }));

//  it('should create the app', async(() => {
//    console.info("\n\nit(should create the app)") ;
//    const fixture = TestBed.createComponent(AppComponent);
//    const app = fixture.debugElement.componentInstance;
//    expect(app).toBeTruthy();
//  }));

//  it(`should have as title 'Sheet Music'`, async(() => {
//    console.info("\n\nit(should have a title)");
//    const fixture = TestBed.createComponent(AppComponent);
//    const app = fixture.debugElement.componentInstance;
//    expect(app.title).toEqual('Sheet Music');
//  }));

//  it('should render title in a h1 tag', async(() => {
//    console.info("\n\nit(should render a title)");
//    const fixture = TestBed.createComponent(AppComponent);
//    fixture.detectChanges();
//    const compiled = fixture.debugElement.nativeElement;
//    expect(compiled.querySelector('h1').textContent).toContain('Sheet Music');
//  }));
});
