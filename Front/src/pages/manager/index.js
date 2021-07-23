import React from 'react';
import {Grid} from '@material-ui/core';

import {Body, Typography} from '../../components/Fragments';
import {HeaderComponent} from '../../components/Header';
import {ClickableCard} from '../../components/ClickableCard';
import {generateNewTicket, callNextTicket, resetCouting} from '../../actions';
import {ORDINARY, PREFERENTIAL} from '../ticketTypes';

class ManagerComponent extends React.Component {

  render() {
    return (
      <Body>
        <HeaderComponent />
        <Grid container>
          <Grid xs={12} sm={6} lg={3}>
            <ClickableCard handleClick={callNextTicket}>
              <Typography color='black' size={18}>Chamar Próximo</Typography>
            </ClickableCard>
          </Grid>
          <Grid xs={12} sm={6} lg={3}>
            <ClickableCard handleClick={resetCouting}>
              <Typography color='black' size={18}>Reiniciar Contagem</Typography>
            </ClickableCard>
          </Grid>
          <Grid xs={12} sm={6} lg={3}>
            <ClickableCard handleClick={generateNewTicket(ORDINARY)}>
              <Typography color='black' size={18}>Nova Senha NORMAL</Typography>
            </ClickableCard>
          </Grid>
          <Grid xs={12} sm={6} lg={3}>
            <ClickableCard handleClick={generateNewTicket(PREFERENTIAL)}>
              <Typography color='black' size={18}>Nova Senha PREFERENCIAL</Typography>
            </ClickableCard>
          </Grid>
        </Grid>
      </Body>
    );
  }
}

export default ManagerComponent;