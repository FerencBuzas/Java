import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By }              from '@angular/platform-browser';
import { DebugElement }    from '@angular/core';

import { ComposersComponent } from './composers.component';

describe('ComposersComponent (templateUrl)', () => {

  let comp:    ComposersComponent;
  let fixture: ComponentFixture<ComposersComponent>;
  let de:      DebugElement;
  let el:      HTMLElement;

  // async beforeEach
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ComposersComponent ]
    })
    .compileComponents();
  }));

  // synchronous beforeEach
  beforeEach(() => {
    fixture = TestBed.createComponent(ComposersComponent);
    comp = fixture.componentInstance;

    // query for the title <h2> by CSS element selector
    de = fixture.debugElement.query(By.css('h2'));
    el = de.nativeElement;
  });

  it('no title in the DOM until manually call `detectChanges`', () => {
    expect(el.textContent).toEqual('');
  });

  it('should display original title', () => {
    fixture.detectChanges();
    expect(el.textContent).toContain("Composers");
  });

//  it('should display a different test title', () => {
//    comp.title = 'Test Title';
//    fixture.detectChanges();
//    expect(el.textContent).toContain('Test Title');
//  });

});
