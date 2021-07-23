import styled from 'styled-components';

export const Body = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  background-color: rgba(0, 102, 160, 1);
`;

export const Panel = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  background-color: rgba(0, 102, 160, 1);
`;

export const MainPanel = styled.div`
position: relative;
flex-grow: 1;
height: 100%;
outline: solid #6791a8;
`;

export const Header = styled.div`
width: 100%;
height: 100px;
position: relative;
display: flex;
justify-content: center;
align-items: center;
background-color: white;
outline: solid #6791a8;
`;

export const MainPanelBody = styled.div`
  height: calc(100% - 200px);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const MainPanelFooter = styled.div`
  height: 100px;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: black;
  color: white;
  outline: solid #6791a8;
`;

export const SidePanel = styled.div`
  position: relative;
  width: 30%;
  height: calc(100% - 100px);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const MessageWrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

export const Typography = styled.h1`
  font-family: ${({family = 'Poppins,sans-serif'}) => family};
  color: ${({color = 'white'}) => color};
  font-size: ${({size = 12}) => size}px;
`;