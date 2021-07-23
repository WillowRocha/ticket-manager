import {format} from 'date-fns';
import React from 'react';
import styled from 'styled-components';
import {AccessTime, CalendarToday} from '@material-ui/icons';

const Container = styled.div`
  display: flex;
  flex-direction: row;
`;

const Span = styled.span`
  margin-left: ${({left = 0}) => left}px;
  display: flex;
  padding: 8px;
  align-items: center;
  font-size: 24px;
`;

class DateTimeComponent extends React.Component {
  
  intervalId = null;
  state = {currentDate: new Date()};

  componentDidMount() {
    this.timeRefresh();
  }

  componentWillUnmount() {
    clearInterval(this.intervalId);
  }

  timeRefresh = () => {
    this.intervalId = setInterval(() => this.setState({currentDate: new Date()}), 1000);
  };

  render() {
    const {currentDate} = this.state;
    return (
      <Container>
        <Span><CalendarToday /></Span>
        <Span>{format(currentDate, 'yyyy-MM-dd')}</Span>
        <Span left={20}><AccessTime /></Span>
        <Span>{format(currentDate, ' HH:mm:ss')}</Span>
      </Container>
    );
  }
}

export default DateTimeComponent;