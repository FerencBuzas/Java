'use strict'; // necessary for es6 output in node

import { browser, element, by, ElementFinder, ElementArrayFinder } from 'protractor';
import { promise } from 'selenium-webdriver';

const expectedH1 = 'Tour of Heroes';
const expectedTitle = `Angular ${expectedH1}`;
const targetHero = { id: 14, name: 'Celeritas' };
const targetHeroDashboardIndex = 3;
const nameSuffix = 'X';
const newHeroName = targetHero.name + nameSuffix;

class Composer {
  id: number;
  name: string;

  // Factory methods

  // Hero from string formatted as '<id> <name>'.
  static fromString(s: string): Hero {
    return {
      id: +s.substr(0, s.indexOf(' ')),
      name: s.substr(s.indexOf(' ') + 1),
    };
  }

  // Hero from composer list <li> element.
  static async fromLi(li: ElementFinder): Promise<Hero> {
      let strings = await li.all(by.xpath('span')).getText();
      return { id: +strings[0], name: strings[1] };
  }

  // Hero id and name from the given detail element.
  static async fromDetail(detail: ElementFinder): Promise<Hero> {
    // Get composer id from the first <div>
    let _id = await detail.all(by.css('div')).first().getText();
    // Get name from the h2
    let _name = await detail.element(by.css('h2')).getText();
    return {
        id: +_id.substr(_id.indexOf(' ') + 1),
        name: _name.substr(0, _name.lastIndexOf(' '))
    };
  }
}

describe('Tutorial part 6', () => {

  beforeAll(() => browser.get(''));

  function getPageElts() {
    let navElts = element.all(by.css('my-app nav a'));

    return {
      navElts: navElts,

      myDashboardHref: navElts.get(0),
      myDashboard: element(by.css('my-app my-dashboard')),
      topHeroes: element.all(by.css('my-app my-dashboard > div h4')),

      myHeroesHref: navElts.get(1),
      myHeroes: element(by.css('my-app my-composers')),
      allHeroes: element.all(by.css('my-app my-composers li')),
      selectedHero: element(by.css('my-app li.selected')),
      selectedHeroSubview: element(by.css('my-app my-composers > div:last-child')),

      heroDetail: element(by.css('my-app composer-detail > div')),

      searchBox: element(by.css('#search-box')),
      searchResults: element.all(by.css('.search-result'))
    };
  }

  describe('Initial page', () => {

    it(`has title '${expectedTitle}'`, () => {
        expect(browser.getTitle()).toEqual(expectedTitle);
    });

    it(`has h1 '${expectedH1}'`, () => {
        expectHeading(1, expectedH1);
    });

    const expectedViewNames = ['Dashboard', 'Heroes'];
    it(`has views ${expectedViewNames}`, () => {
      let viewNames = getPageElts().navElts.map((el: ElementFinder) => el.getText());
      expect(viewNames).toEqual(expectedViewNames);
    });

    it('has dashboard as the active view', () => {
      let page = getPageElts();
      expect(page.myDashboard.isPresent()).toBeTruthy();
    });

  });

  describe('Dashboard tests', () => {

    beforeAll(() => browser.get(''));

    it('has top composers', () => {
      let page = getPageElts();
      expect(page.topHeroes.count()).toEqual(4);
    });

    it(`selects and routes to ${targetHero.name} details`, dashboardSelectTargetHero);

    it(`updates composer name (${newHeroName}) in details view`, updateHeroNameInDetailView);

    it(`cancels and shows ${targetHero.name} in Dashboard`, () => {
      element(by.buttonText('Back')).click();
      browser.waitForAngular(); // seems necessary to gets tests to past for toh-pt6

      let targetHeroElt = getPageElts().topHeroes.get(targetHeroDashboardIndex);
      expect(targetHeroElt.getText()).toEqual(targetHero.name);
    });

    it(`selects and routes to ${targetHero.name} details`, dashboardSelectTargetHero);

    it(`updates composer name (${newHeroName}) in details view`, updateHeroNameInDetailView);

    it(`saves and shows ${newHeroName} in Dashboard`, () => {
      element(by.buttonText('Save')).click();
      browser.waitForAngular(); // seems necessary to gets tests to past for toh-pt6

      let targetHeroElt = getPageElts().topHeroes.get(targetHeroDashboardIndex);
      expect(targetHeroElt.getText()).toEqual(newHeroName);
    });

  });

  describe('Heroes tests', () => {

    beforeAll(() => browser.get(''));

    it('can switch to Heroes view', () => {
      getPageElts().myHeroesHref.click();
      let page = getPageElts();
      expect(page.myHeroes.isPresent()).toBeTruthy();
      expect(page.allHeroes.count()).toEqual(11, 'number of composers');
    });

    it(`selects and shows ${targetHero.name} as selected in list`, () => {
      getHeroLiEltById(targetHero.id).click();
      expect(Hero.fromLi(getPageElts().selectedHero)).toEqual(targetHero);
    });

    it('shows selected composer subview', () => {
      let page = getPageElts();
      let title = page.selectedHeroSubview.element(by.css('h2')).getText();
      let expectedTitle = `${targetHero.name.toUpperCase()} is my composer`;
      expect(title).toEqual(expectedTitle);
    });

    it('can route to composer details', () => {
      element(by.buttonText('View Details')).click();

      let page = getPageElts();
      expect(page.heroDetail.isPresent()).toBeTruthy('shows composer detail');
      let composer = Hero.fromDetail(page.heroDetail);
      expect(composer).toEqual(targetHero);
    });

    it(`updates composer name (${newHeroName}) in details view`, updateHeroNameInDetailView);

    it(`shows ${newHeroName} in Heroes list`, () => {
      element(by.buttonText('Save')).click();
      browser.waitForAngular(); // seems necessary to gets tests to past for toh-pt6
      let expectedHero = {id: targetHero.id, name: newHeroName};
      expect(Hero.fromLi(getHeroLiEltById(targetHero.id))).toEqual(expectedHero);
    });

    it(`deletes ${newHeroName} from Heroes list`, async () => {
      const composersBefore = await toHeroArray(getPageElts().allHeroes);
      const li = getHeroLiEltById(targetHero.id);
      li.element(by.buttonText('x')).click();

      const page = getPageElts();
      expect(page.myHeroes.isPresent()).toBeTruthy();
      expect(page.allHeroes.count()).toEqual(10, 'number of composers');
      const composersAfter = await toHeroArray(page.allHeroes);
      const expectedHeroes =  composersBefore.filter(h => h.name !== newHeroName);
      expect(composersAfter).toEqual(expectedHeroes);
      expect(page.selectedHeroSubview.isPresent()).toBeFalsy();
    });

    it(`adds back ${targetHero.name}`, async () => {
      const newHeroName = 'Alice';
      const composersBefore = await toHeroArray(getPageElts().allHeroes);
      const numHeroes = composersBefore.length;

      element(by.css('input')).sendKeys(newHeroName);
      element(by.buttonText('Add')).click();

      let page = getPageElts();
      let composersAfter = await toHeroArray(page.allHeroes);
      expect(composersAfter.length).toEqual(numHeroes + 1, 'number of composers');

      expect(composersAfter.slice(0, numHeroes)).toEqual(composersBefore, 'Old composers are still there');

      const maxId = composersBefore[composersBefore.length - 1].id;
      expect(composersAfter[numHeroes]).toEqual({id: maxId + 1, name: newHeroName});
    });
  });

  describe('Progressive composer search', () => {

    beforeAll(() => browser.get(''));

    it(`searches for 'Ce'`, async () => {
      getPageElts().searchBox.sendKeys('Ce');
      browser.sleep(1000);
      expect(getPageElts().searchResults.count()).toBe(2);
    });

    it(`continues search with 'l'`, async () => {
      getPageElts().searchBox.sendKeys('l');
      browser.sleep(1000);
      expect(getPageElts().searchResults.count()).toBe(1);
    });

    it(`continues search with 'e' and gets ${targetHero.name}`, async () => {
      getPageElts().searchBox.sendKeys('e');
      browser.sleep(1000);
      let page = getPageElts();
      expect(page.searchResults.count()).toBe(1);
      let composer = page.searchResults.get(0);
      expect(composer.getText()).toEqual(targetHero.name);
    });

    it(`navigates to ${targetHero.name} details view`, async () => {
      let composer = getPageElts().searchResults.get(0);
      expect(composer.getText()).toEqual(targetHero.name);
      composer.click();

      let page = getPageElts();
      expect(page.heroDetail.isPresent()).toBeTruthy('shows composer detail');
      expect(Hero.fromDetail(page.heroDetail)).toEqual(targetHero);
    });
  });

  function dashboardSelectTargetHero() {
    let targetHeroElt = getPageElts().topHeroes.get(targetHeroDashboardIndex);
    expect(targetHeroElt.getText()).toEqual(targetHero.name);
    targetHeroElt.click();
    browser.waitForAngular(); // seems necessary to gets tests to past for toh-pt6

    let page = getPageElts();
    expect(page.heroDetail.isPresent()).toBeTruthy('shows composer detail');
    let composer = Hero.fromDetail(page.heroDetail);
    expect(composer).toEqual(targetHero);
  }

  async function updateHeroNameInDetailView() {
    // Assumes that the current view is the composer details view.
    addToHeroName(nameSuffix);

    let composer = await Hero.fromDetail(getPageElts().heroDetail);
    expect(composer).toEqual({id: targetHero.id, name: newHeroName});
  }

});

function addToHeroName(text: string): promise.Promise<void> {
  let input = element(by.css('input'));
  return input.sendKeys(text);
}

function expectHeading(hLevel: number, expectedText: string): void {
    let hTag = `h${hLevel}`;
    let hText = element(by.css(hTag)).getText();
    expect(hText).toEqual(expectedText, hTag);
};

function getHeroLiEltById(id: number): ElementFinder {
  let spanForId = element(by.cssContainingText('li span.badge', id.toString()));
  return spanForId.element(by.xpath('..'));
}

async function toHeroArray(allHeroes: ElementArrayFinder): Promise<Hero[]> {
  let promisedHeroes = await allHeroes.map(Hero.fromLi);
  // The cast is necessary to get around issuing with the signature of Promise.all()
  return <Promise<any>> Promise.all(promisedHeroes);
}
