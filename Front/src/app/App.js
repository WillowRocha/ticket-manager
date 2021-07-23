import React from 'react';
import {BrowserRouter, Route, Switch} from 'react-router-dom';

import ManagerComponent from '../pages/manager';
import PanelComponent from '../pages/panel';


function App() {
  return (
    <BrowserRouter>
      <Switch>
        <Route path="/" exact component={PanelComponent} />
        <Route path="/panel" component={PanelComponent} />
        <Route path="/manager" component={ManagerComponent} />
      </Switch>
    </BrowserRouter>
  );
}

export default App;
