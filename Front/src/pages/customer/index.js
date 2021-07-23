import React from 'react';
import {Grid} from '@material-ui/core';

import {Body, MessageWrapper, Typography} from '../../components/Fragments';
import {HeaderComponent} from '../../components/Header';
import {ClickableCard} from '../../components/ClickableCard';
import {generateNewTicket} from '../../actions';
import {ORDINARY, PREFERENTIAL} from '../ticketTypes';

class CustomerComponent extends React.Component {

  render() {
    return (
      <Body>
        <HeaderComponent />
        <MessageWrapper>
          <Typography size={32}>Seja bem vindo!</Typography>
          <Typography size={16}>Por favor, retire uma senha e aguarde</Typography>
        </MessageWrapper>
        <Grid container>
          <Grid xs={0} sm={0} lg={3}></Grid>
          <Grid xs={12} sm={6} lg={3}>
            <ClickableCard handleClick={generateNewTicket(ORDINARY)}>
              <Typography color='black' size={20}>NORMAL</Typography>
            </ClickableCard>
          </Grid>
          <Grid xs={12} sm={6} lg={3}>
            <ClickableCard handleClick={generateNewTicket(PREFERENTIAL)}>
              <Typography color='black' size={20}>PREFERENCIAL</Typography>
            </ClickableCard>
          </Grid>
          <Grid xs={0} sm={0} lg={3}></Grid>
        </Grid>
      </Body>
    );
  }
}

export default CustomerComponent;