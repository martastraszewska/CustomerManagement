import React from 'react';

import { Container, Typography } from '@mui/material';

export const Home = () => (
  <Container sx={{ textAlign: 'center', my: '1rem' }}>
    <Typography variant='h2'>Witaj w aplikacji Customer Management. Aby kontynuowac, zaloguj sie.</Typography>
  </Container>
);