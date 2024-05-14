import { useState, useEffect } from "react";

import { Button, Box } from "@mui/material";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";
import TextField from "@mui/material/TextField";

interface CustomerDetailsDialogComponentProps {
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
  };
  onClose: () => void;
}

export const CustomerDetailsDialog = ({
  open,
  onClose,
  currentRow,
}: CustomerDetailsDialogComponentProps) => {
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

  useEffect(() => {
    setFirstName(currentRow.firstName);
    setLastName(currentRow.lastName);
    setCompany(currentRow.company);
    setCity(currentRow.city);
    setStreet(currentRow.street);
    setPhoneNumber(currentRow.phoneNumber);
    setEmailAddress(currentRow.emailAddress);
    setLastOverviewDate(currentRow.lastOverviewDate);
  }, [
    currentRow.firstName,
    currentRow.lastName,
    currentRow.company,
    currentRow.city,
    currentRow.street,
    currentRow.phoneNumber,
    currentRow.emailAddress,
    currentRow.lastOverviewDate,
  ]);

  const handleCloseDetailsDialog = () => closeDialog();

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
      onClose={handleCloseDetailsDialog}
      disableRestoreFocus
    >
      <DialogTitle>Szczegoly klienta</DialogTitle>

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
            inputProps={{ readOnly: true }}
          />
          <TextField
            id="outlined-basic"
            label="Nazwisko"
            variant="outlined"
            defaultValue={currentRow.lastName}
            inputProps={{ readOnly: true }}
          />
          <TextField
            id="outlined-basic"
            label="Firma"
            variant="outlined"
            defaultValue={currentRow.company}
            inputProps={{ readOnly: true }}
          />
          <TextField
            id="outlined-basic"
            label="Miasto"
            variant="outlined"
            defaultValue={currentRow.city}
            inputProps={{ readOnly: true }}
          />
          <TextField
            id="outlined-basic"
            label="Ulica"
            variant="outlined"
            defaultValue={currentRow.street}
            inputProps={{ readOnly: true }}
          />
          <TextField
            id="outlined-basic"
            label="Numer telefonu"
            variant="outlined"
            defaultValue={currentRow.phoneNumber}
            inputProps={{ readOnly: true }}
          />
          <TextField
            id="outlined-basic"
            label="Adres e-mail"
            variant="outlined"
            defaultValue={currentRow.emailAddress}
            inputProps={{ readOnly: true }}
          />
          <TextField
            id="outlined-basic"
            label="Data ostatniego przegladu"
            variant="outlined"
            defaultValue={currentRow.lastOverviewDate}
            inputProps={{ readOnly: true }}
          ></TextField>
        </Box>
      </DialogContent>

      <DialogActions>
        <Button onClick={handleCloseDetailsDialog}>Zamknij</Button>
      </DialogActions>
    </Dialog>
  );
};
