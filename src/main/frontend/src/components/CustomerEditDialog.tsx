import { useState, useEffect } from "react";

import { Button, Box } from "@mui/material";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";
import TextField from "@mui/material/TextField";

interface CustomerEditDialogComponentProps {
  open: boolean;
  currentRow: {
    id: string;
    firstName: string;
    lastName: string;
    company: string;
    city: string;
    street: string;
    phoneNumber: string;
    emailAddress: string;
    lastOverviewDate: string;
    lat: number;
    lng: number;
  };
  onClose: () => void;
  onEditCustomer: (
    id: string,
    firstName: string,
    lastName: string,
    company: string,
    city: string,
    street: string,
    phoneNumber: string,
    emailAddress: string,
    lastOverviewDate: string,
    lat: number,
    lng: number,
  ) => void;
}

export const CustomerEditDialog = ({
  open,
  onClose,
  onEditCustomer,
  currentRow,
}: CustomerEditDialogComponentProps) => {
  const [id, setId] = useState(currentRow.id);
  const [firstName, setFirstName] = useState(currentRow.firstName);
  const [lastName, setLastName] = useState(currentRow.lastName);
  const [company, setCompany] = useState(currentRow.company);
  const [city, setCity] = useState(currentRow.city);
  const [street, setStreet] = useState(currentRow.street);
  const [phoneNumber, setPhoneNumber] = useState(currentRow.phoneNumber);
  const [emailAddress, setEmailAddress] = useState(currentRow.emailAddress);
  const [lastOverviewDate, setLastOverviewDate] = useState(
    currentRow.lastOverviewDate
  );
  const[lat, setLat] = useState(currentRow.lat);
  const[lng, setLng] = useState(currentRow.lng);

  useEffect(() => {
    setId(currentRow.id);
    setFirstName(currentRow.firstName);
    setLastName(currentRow.lastName);
    setCompany(currentRow.company);
    setCity(currentRow.city);
    setStreet(currentRow.street);
    setPhoneNumber(currentRow.phoneNumber);
    setEmailAddress(currentRow.emailAddress);
    setLastOverviewDate(currentRow.lastOverviewDate);
    setLat(currentRow.lat);
    setLng(currentRow.lng);
  }, [
    currentRow.id,
    currentRow.firstName,
    currentRow.lastName,
    currentRow.company,
    currentRow.city,
    currentRow.street,
    currentRow.phoneNumber,
    currentRow.emailAddress,
    currentRow.lastOverviewDate,
    currentRow.lat,
    currentRow.lng
  ]);

  const handleCloseEditDialog = () => closeDialog();

  const handleSaveEditDialog = async () => {
    onEditCustomer(
      id,
      firstName,
      lastName,
      company,
      city,
      street,
      phoneNumber,
      emailAddress,
      lastOverviewDate,
      lat,
      lng
    );
    closeDialog();
  };

  const closeDialog = () => {
    setFirstName("");
    setLastName("");
    setCompany("");
    setCity("");
    setStreet("");
    setPhoneNumber("");
    setEmailAddress("");
    setLastOverviewDate("");
    setLat(0);
    setLng(0);
    onClose();
  };

  return (
    <Dialog
      fullWidth={true}
      open={open}
      onClose={handleCloseEditDialog}
      disableRestoreFocus
    >
      <DialogTitle>Edytuj dane klienta</DialogTitle>

      <DialogContent>
        <Box
          component="form"
          sx={{
            "& .MuiTextField-root": { m: 1, width: "25ch" },
          }}
          noValidate
          autoComplete="off"
        >
          <TextField
            id="outlined-basic"
            label="Imie"
            variant="outlined"
            defaultValue={currentRow.firstName}
            onChange={(event) => {
              setFirstName(event.target.value);
            }}
          />
          <TextField
            id="outlined-basic"
            label="Nazwisko"
            variant="outlined"
            defaultValue={currentRow.lastName}
            onChange={(event) => {
              setLastName(event.target.value);
            }}
          />
          <TextField
            id="outlined-basic"
            label="Firma"
            variant="outlined"
            defaultValue={currentRow.company}
            onChange={(event) => {
              setCompany(event.target.value);
            }}
          />
          <TextField
            id="outlined-basic"
            label="Miasto"
            variant="outlined"
            defaultValue={currentRow.city}
            onChange={(event) => {
              setCity(event.target.value);
            }}
          />
          <TextField
            id="outlined-basic"
            label="Ulica"
            variant="outlined"
            defaultValue={currentRow.street}
            onChange={(event) => {
              setStreet(event.target.value);
            }}
          />
          <TextField
            id="outlined-basic"
            label="Numer telefonu"
            variant="outlined"
            defaultValue={currentRow.phoneNumber}
            onChange={(event) => {
              setPhoneNumber(event.target.value);
            }}
          />
          <TextField
            id="outlined-basic"
            label="Adres e-mail"
            variant="outlined"
            defaultValue={currentRow.emailAddress}
            onChange={(event) => {
              setEmailAddress(event.target.value);
            }}
          />
          <TextField
            id="outlined-basic"
            label="Data ostatniego przegladu"
            variant="outlined"
            defaultValue={currentRow.lastOverviewDate}
            onChange={(event) => {
              setLastOverviewDate(event.target.value);
            }}
          ></TextField>
        </Box>
      </DialogContent>

      <DialogActions>
        <Button onClick={handleCloseEditDialog}>Cofnij</Button>
        <Button
          onClick={handleSaveEditDialog}
          disabled={
            (firstName.length === 0 || lastName.length === 0) &&
            company.length === 0
          }
        >
          Zapisz
        </Button>
      </DialogActions>
    </Dialog>
  );
};
