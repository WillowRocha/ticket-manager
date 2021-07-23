import {Card} from '@material-ui/core';
import React from 'react';
import styled from 'styled-components';

const Clickable = styled.div`
  cursor: pointer;
  padding: 24px;
`;

const CardBody = styled.div`
  min-width: 150px;
  min-height: 100px;
  width: 100%;
  height; 100%;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
`;


export const ClickableCard = props => {

  const {children, handleClick} = props;
  return (
    <Clickable onClick={handleClick} >
      <Card>
        <CardBody>
          {children}
        </CardBody>
      </Card>
    </Clickable>
  );
}