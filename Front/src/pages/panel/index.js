import {map} from 'lodash';
import React from 'react';
import {connect} from 'react-redux';

import {loadData} from '../../actions';
import DateTimeComponent from './components/dateTimeComponent';
import {Panel, MainPanel, Body, MainPanelBody, MainPanelFooter, SidePanel, Typography} from '../../components/Fragments';
import {HeaderComponent} from '../../components/Header';

class PanelComponent extends React.Component {

  intervalId = null;
  interval = 5000;

  componentDidMount() {
    this.autoRefresh();
  }

  componentWillUnmount() {
    clearInterval(this.intervalId);
  }

  autoRefresh = () => {
    this.intervalId = setInterval(this.props.loadData, this.interval);
  };

  render() {
    const {data: {currentTicketCode, lastTickets}} = this.props;

    return (
      <Body>
        <Panel>
          <MainPanel>
            <HeaderComponent />
            <MainPanelBody>
              <Typography size={48}>SENHA ATUAL</Typography>
              <Typography size={180}>{currentTicketCode}</Typography>
            </MainPanelBody>
            <MainPanelFooter>
              <Typography size={20}>
                <DateTimeComponent />
              </Typography>
            </MainPanelFooter>
          </MainPanel>
          <SidePanel>
            <Typography size={24}>SENHAS ANTERIORES</Typography>
            <div>
              {map(lastTickets, it => (
                <Typography size={50}>{it}</Typography>
              ))}
            </div>
          </SidePanel>
        </Panel>
      </Body>
    );
  }
}

export default connect(state => ({data: {...state.ticketsData}}), {loadData})(PanelComponent);