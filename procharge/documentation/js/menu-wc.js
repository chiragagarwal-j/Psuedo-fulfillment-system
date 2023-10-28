'use strict';

customElements.define('compodoc-menu', class extends HTMLElement {
    constructor() {
        super();
        this.isNormalMode = this.getAttribute('mode') === 'normal';
    }

    connectedCallback() {
        this.render(this.isNormalMode);
    }

    render(isNormalMode) {
        let tp = lithtml.html(`
        <nav>
            <ul class="list">
                <li class="title">
                    <a href="index.html" data-type="index-link">procharge documentation</a>
                </li>

                <li class="divider"></li>
                ${ isNormalMode ? `<div id="book-search-input" role="search"><input type="text" placeholder="Type to search"></div>` : '' }
                <li class="chapter">
                    <a data-type="chapter-link" href="index.html"><span class="icon ion-ios-home"></span>Getting started</a>
                    <ul class="links">
                        <li class="link">
                            <a href="overview.html" data-type="chapter-link">
                                <span class="icon ion-ios-keypad"></span>Overview
                            </a>
                        </li>
                        <li class="link">
                            <a href="index.html" data-type="chapter-link">
                                <span class="icon ion-ios-paper"></span>README
                            </a>
                        </li>
                                <li class="link">
                                    <a href="dependencies.html" data-type="chapter-link">
                                        <span class="icon ion-ios-list"></span>Dependencies
                                    </a>
                                </li>
                                <li class="link">
                                    <a href="properties.html" data-type="chapter-link">
                                        <span class="icon ion-ios-apps"></span>Properties
                                    </a>
                                </li>
                    </ul>
                </li>
                    <li class="chapter modules">
                        <a data-type="chapter-link" href="modules.html">
                            <div class="menu-toggler linked" data-bs-toggle="collapse" ${ isNormalMode ?
                                'data-bs-target="#modules-links"' : 'data-bs-target="#xs-modules-links"' }>
                                <span class="icon ion-ios-archive"></span>
                                <span class="link-name">Modules</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                        </a>
                        <ul class="links collapse " ${ isNormalMode ? 'id="modules-links"' : 'id="xs-modules-links"' }>
                            <li class="link">
                                <a href="modules/AppModule.html" data-type="entity-link" >AppModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ?
                                            'data-bs-target="#components-links-module-AppModule-f8b0b59419e42a9a79f479569134833f61e6d52211911bf0f914130b1efe9d28dfe82b7573e1a29ffd3b3063a2a4d658f6366888f288a19509ff7ebe3dfcfc82"' : 'data-bs-target="#xs-components-links-module-AppModule-f8b0b59419e42a9a79f479569134833f61e6d52211911bf0f914130b1efe9d28dfe82b7573e1a29ffd3b3063a2a4d658f6366888f288a19509ff7ebe3dfcfc82"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-AppModule-f8b0b59419e42a9a79f479569134833f61e6d52211911bf0f914130b1efe9d28dfe82b7573e1a29ffd3b3063a2a4d658f6366888f288a19509ff7ebe3dfcfc82"' :
                                            'id="xs-components-links-module-AppModule-f8b0b59419e42a9a79f479569134833f61e6d52211911bf0f914130b1efe9d28dfe82b7573e1a29ffd3b3063a2a4d658f6366888f288a19509ff7ebe3dfcfc82"' }>
                                            <li class="link">
                                                <a href="components/AppComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AppComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ConfirmationDialogComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ConfirmationDialogComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/GetNewSimComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >GetNewSimComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/HomeComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >HomeComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/NavbarComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >NavbarComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/OrderStatusComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >OrderStatusComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/PaymentPageComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >PaymentPageComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/RechargeComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >RechargeComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/RechargeOrderStatusComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >RechargeOrderStatusComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/RechargePlansComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >RechargePlansComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/TrackOrderComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >TrackOrderComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/AppRoutingModule.html" data-type="entity-link" >AppRoutingModule</a>
                            </li>
                </ul>
                </li>
                        <li class="chapter">
                            <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#injectables-links"' :
                                'data-bs-target="#xs-injectables-links"' }>
                                <span class="icon ion-md-arrow-round-down"></span>
                                <span>Injectables</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                            <ul class="links collapse " ${ isNormalMode ? 'id="injectables-links"' : 'id="xs-injectables-links"' }>
                                <li class="link">
                                    <a href="injectables/NewSimService.html" data-type="entity-link" >NewSimService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/RechargeSimService.html" data-type="entity-link" >RechargeSimService</a>
                                </li>
                            </ul>
                        </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#interfaces-links"' :
                            'data-bs-target="#xs-interfaces-links"' }>
                            <span class="icon ion-md-information-circle-outline"></span>
                            <span>Interfaces</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? ' id="interfaces-links"' : 'id="xs-interfaces-links"' }>
                            <li class="link">
                                <a href="interfaces/FetchMobileNumber.html" data-type="entity-link" >FetchMobileNumber</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/FetchOrderID.html" data-type="entity-link" >FetchOrderID</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/FetchPlans.html" data-type="entity-link" >FetchPlans</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/NetworkOperator.html" data-type="entity-link" >NetworkOperator</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/NewSim.html" data-type="entity-link" >NewSim</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/NewSimOrderStatus.html" data-type="entity-link" >NewSimOrderStatus</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/OperatorPlans.html" data-type="entity-link" >OperatorPlans</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/Plan.html" data-type="entity-link" >Plan</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/RechargeInfo.html" data-type="entity-link" >RechargeInfo</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/RechargeOrderStatus.html" data-type="entity-link" >RechargeOrderStatus</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/RechargePlans.html" data-type="entity-link" >RechargePlans</a>
                            </li>
                        </ul>
                    </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#miscellaneous-links"'
                            : 'data-bs-target="#xs-miscellaneous-links"' }>
                            <span class="icon ion-ios-cube"></span>
                            <span>Miscellaneous</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? 'id="miscellaneous-links"' : 'id="xs-miscellaneous-links"' }>
                            <li class="link">
                                <a href="miscellaneous/variables.html" data-type="entity-link">Variables</a>
                            </li>
                        </ul>
                    </li>
                        <li class="chapter">
                            <a data-type="chapter-link" href="routes.html"><span class="icon ion-ios-git-branch"></span>Routes</a>
                        </li>
                    <li class="chapter">
                        <a data-type="chapter-link" href="coverage.html"><span class="icon ion-ios-stats"></span>Documentation coverage</a>
                    </li>
                    <li class="divider"></li>
                    <li class="copyright">
                        Documentation generated using <a href="https://compodoc.app/" target="_blank" rel="noopener noreferrer">
                            <img data-src="images/compodoc-vectorise.png" class="img-responsive" data-type="compodoc-logo">
                        </a>
                    </li>
            </ul>
        </nav>
        `);
        this.innerHTML = tp.strings;
    }
});