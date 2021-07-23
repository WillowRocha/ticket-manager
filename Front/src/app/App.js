import React from 'react';
import {BrowserRouter, Route, Switch} from 'react-router-dom';

import CustomerComponent from '../pages/customer';
import ManagerComponent from '../pages/manager';
import PanelComponent from '../pages/panel';


function App() {
  return (
    <BrowserRouter>
      <Switch>
        <Route path="/" exact component={PanelComponent} />
        <Route path="/panel" component={PanelComponent} />
        <Route path="/customer" component={CustomerComponent} />
        <Route path="/manager" component={ManagerComponent} />
      </Switch>
    </BrowserRouter>
  );
}

export default App;
