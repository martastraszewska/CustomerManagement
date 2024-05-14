import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { AccountCircle } from '@mui/icons-material';
import LoginIcon from '@mui/icons-material/Login';
import MenuIcon from '@mui/icons-material/Menu';
import { AppBar, Box, IconButton, Menu, MenuItem, Toolbar, Typography } from '@mui/material';

import { PATH } from '../app/constants';


const MENU_ITEMS = [
  {
    title: 'Home',
    link: PATH.HOME,
  },
  {
    title: 'Customers-Table',
    link: PATH.CUSTOMERS_TABLE,
  },

];

// export const NavigationBar = () => {
//   const accessToken = useAppSelector((state: RootState) => state.auth.accessToken);
//   const dispatch = useAppDispatch();
//   const navigate = useNavigate();
//   const [menuAnchorEl, setMenuAnchorEl] = useState<null | HTMLElement>(null);
//   const [loginAnchorEl, setLoginAnchorEl] = useState<null | HTMLElement>(null);

//   const handleMenuOpen = (event: React.MouseEvent<HTMLElement>) => setMenuAnchorEl(event.currentTarget);
//   const handleMenuClose = () => setMenuAnchorEl(null);

//   const handleLoginOpen = (event: React.MouseEvent<HTMLElement>) => setLoginAnchorEl(event.currentTarget);
//   const handleLoginClose = () => setLoginAnchorEl(null);

//   const handleLogin = () => getAuthorizationCodeWithPKCE(onSaveCodeVerifier);
//   const onSaveCodeVerifier = (codeVerifier: string) => dispatch(saveCodeVerifier(codeVerifier));

//   const handleMenuItemClick = (link: string) => {
//     navigate(link);
//     handleMenuClose();
//   };

//   const handleLogout = () => {
//     dispatch(logout());
//     handleLoginClose();
//   };

//   return (
//     <Box sx={{ flexGrow: 1 }}>
//       <AppBar position='static'>
//         <Toolbar>
//           <IconButton size='large' edge='start' color='inherit' sx={{ mr: 2 }} onClick={handleMenuOpen}>
//             <MenuIcon />
//           </IconButton>
//           <Menu
//             id='menu-appbar'
//             anchorEl={menuAnchorEl}
//             anchorOrigin={{
//               vertical: 'top',
//               horizontal: 'right',
//             }}
//             keepMounted
//             transformOrigin={{
//               vertical: 'top',
//               horizontal: 'right',
//             }}
//             open={Boolean(menuAnchorEl)}
//             onClose={handleMenuClose}
//           >
//             {MENU_ITEMS.map(menuItem =>
//               <MenuItem key={menuItem.title} onClick={() => handleMenuItemClick(menuItem.link)}>
//                 {menuItem.title}
//               </MenuItem>,
//             )}
//           </Menu>
//           <Typography variant='h6' component='div' sx={{ flexGrow: 1 }}>
//             Admin tool
//           </Typography>
//           {accessToken ? (
//             <>
//               <IconButton size='large' color='inherit' onClick={handleLoginOpen}>
//                 <AccountCircle />
//               </IconButton>
//               <Menu
//                 id='login-appbar'
//                 anchorEl={loginAnchorEl}
//                 anchorOrigin={{
//                   vertical: 'top',
//                   horizontal: 'right',
//                 }}
//                 keepMounted
//                 transformOrigin={{
//                   vertical: 'top',
//                   horizontal: 'right',
//                 }}
//                 open={Boolean(loginAnchorEl)}
//                 onClose={handleLoginClose}
//               >
//                 <MenuItem onClick={handleLogout}>Logout</MenuItem>
//               </Menu>
//             </>
//           ) : (
//             <IconButton size='large' color='inherit' onClick={handleLogin}>
//               <LoginIcon />
//             </IconButton>
//           )}
//         </Toolbar>
//       </AppBar>
//     </Box>
//   );
// };
