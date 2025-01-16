import { PATH } from "./app/constants";
import { Home } from "./pages/Home";
import { CustomersPage } from "./pages/CustomersPage";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { PersistGate } from "redux-persist/integration/react";
import { Provider } from "react-redux";
import { persistor, store } from "./app/store";
import { useAppDispatch } from "./hooks/reduxCommon";
import { NavigationBar } from "./components/NavigationBar";


const ROUTES = [
  {
    path: PATH.HOME,
    element: <Home />,
    nonProtected: true,
  },
  {
    path: PATH.CUSTOMERS_TABLE,
    element: <CustomersPage />,
    nonProtected: true,
  },
];

const App = () => (
  <BrowserRouter>
    <Provider store={store}>
      <PersistGate loading={null} persistor={persistor}>
        <NavigationBar />
        <Routes>
          {ROUTES.map((route) => (
            <Route
              key={route.path}
              path={route.path}
              element={route.element}
            />
          ))}
        </Routes>
      </PersistGate>
    </Provider>
  </BrowserRouter>
);

export default App;
