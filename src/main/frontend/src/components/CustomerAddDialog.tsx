import { useState } from "react";

import { Button, Box, FormControl } from "@mui/material";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";
import TextField from "@mui/material/TextField";
import useFormControl from "@mui/material";
import { DemoContainer } from "@mui/x-date-pickers/internals/demo";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";

interface CustomerAddDialogComponentProps {
  open: boolean;
  onClose: () => void;
  onAddCustomer: (
    id: string,
    firstName: string,
    lastName: string,
    company: string,
    city: string,
    street: string,
    phoneNumber: string,
    emailAddress: string,
    lastOverviewDate: string
  ) => void;
}

export const CustomerAddDialog = ({
  open,
  onClose,
  onAddCustomer,
}: CustomerAddDialogComponentProps) => {
  const [id, setId] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [company, setCompany] = useState("");
  const [city, setCity] = useState("");
  const [street, setStreet] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [emailAddress, setEmailAddress] = useState("");
  const [lastOverviewDate, setLastOverviewDate] = useState("");

  const handleCloseAddDialog = () => closeDialog();

  const handleSaveAddDialog = async () => {
    onAddCustomer(
      id,
      firstName,
      lastName,
      company,
      city,
      street,
      phoneNumber,
      emailAddress,
      lastOverviewDate
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
    onClose();
  };

  return (
    <Dialog
      fullWidth={true}
      open={open}
      onClose={handleCloseAddDialog}
      disableRestoreFocus
    >
      <DialogTitle>Dodaj nowego klienta</DialogTitle>

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
            onChange={(event) => {
              setFirstName(event.target.value);
            }}
          />
          <TextField
            id="outlined-basic"
            label="Nazwisko"
            variant="outlined"
            onChange={(event) => {
              setLastName(event.target.value);
            }}
          />
          <TextField
            id="outlined-basic"
            label="Firma"
            variant="outlined"
            onChange={(event) => {
              setCompany(event.target.value);
            }}
          />
          <TextField
            id="outlined-basic"
            label="Miasto"
            variant="outlined"
            onChange={(event) => {
              setCity(event.target.value);
            }}
          />
          <TextField
            id="outlined-basic"
            label="Ulica"
            variant="outlined"
            onChange={(event) => {
              setStreet(event.target.value);
            }}
          />
          <TextField
            id="outlined-basic"
            label="Numer telefonu"
            variant="outlined"
            onChange={(event) => {
              setPhoneNumber(event.target.value);
            }}
          />
          <TextField
            id="outlined-basic"
            label="Adres e-mail"
            variant="outlined"
            onChange={(event) => {
              setEmailAddress(event.target.value);
            }}
          />
          <TextField
            id="outlined-basic"
            label="Data ostatniego przegladu"
            variant="outlined"
            onChange={(event) => {
              setLastOverviewDate(event.target.value);
            }}
          ></TextField>
          
         
    
        </Box>
      </DialogContent>

      <DialogActions>
        <Button onClick={handleCloseAddDialog}>Cofnij</Button>
        <Button
          onClick={handleSaveAddDialog}
          disabled={
            firstName.length === 0 ||
            lastName.length === 0 ||
            company.length === 0 ||
            city.length === 0 ||
            street.length === 0 ||
            phoneNumber.length === 0 ||
            emailAddress.length === 0 ||
            lastOverviewDate.length === 0
          }
        >
          Zapisz
        </Button>
      </DialogActions>
    </Dialog>
  );
};
