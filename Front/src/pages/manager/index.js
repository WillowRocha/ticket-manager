import React from 'react';
import {Grid} from '@material-ui/core';

import {Body, Typography} from '../../components/Fragments';
import {HeaderComponent} from '../../components/Header';
import {ClickableCard} from '../../components/ClickableCard';

class ManagerComponent extends React.Component {

  render() {
    return (
      <Body>
        <HeaderComponent />
        <Grid container>
          <Grid xs={3}>
            <ClickableCard handleClick={() => alert('clicou')}>
              <Typography color='black' size={18}>Chamar Próximo</Typography>
            </ClickableCard>
          </Grid>
          <Grid xs={3}>
            <ClickableCard handleClick={() => alert('clicou')}>
              <Typography color='black' size={18}>Reiniciar Contagem</Typography>
            </ClickableCard>
          </Grid>
          <Grid xs={3}>
            <ClickableCard handleClick={() => alert('clicou')}>
              <Typography color='black' size={18}>Nova Senha NORMAL</Typography>
            </ClickableCard>
          </Grid>
          <Grid xs={3}>
            <ClickableCard handleClick={() => alert('clicou')}>
              <Typography color='black' size={18}>Nova Senha PREFERENCIAL</Typography>
            </ClickableCard>
          </Grid>
        </Grid>
      </Body>
    );
  }
}

export default ManagerComponent;