import React from 'react';
import Wrapper from '../../assets/wrappers/SharedLayout';
import { Outlet, Link } from 'react-router-dom';
import { Navbar, SmallSidebar, BigSidebar } from '../../components';

const SharedLayout = () => {
  return (
    <Wrapper>
      <main className="dashboard">
        {/* With css , we determine which side bar will be displayed
        dpenends on the width of the screen */}
        <SmallSidebar></SmallSidebar>
        <BigSidebar></BigSidebar>
        <div>
          <Navbar />
          <div className="dashboard-page">
            <Outlet />
          </div>
        </div>
      </main>
    </Wrapper>
  );
};

export default SharedLayout;
