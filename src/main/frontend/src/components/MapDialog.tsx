"use client";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";
import { Button } from "@mui/material";
import { useEffect, useState } from "react";
import {
  APIProvider,
  Map,
  AdvancedMarker,
  Pin,
  InfoWindow,
} from "@vis.gl/react-google-maps";
import { Marker } from "@vis.gl/react-google-maps";
import { GoogleMapsProvider } from "@ubilabs/google-maps-react-hooks";

import { Customer } from "../models/Customer";
import { addListener } from "process";

type Point = google.maps.LatLngLiteral & {
  key: string;
  firstName: string;
  lastName: string;
  company: string;
  city: string;
  street: string;
  phoneNumber: string;
  emailAddress: string;
  lastOverviewDate: string;
};
type Props = { points: Point[] };

interface MapDialogComponentProps {
  data: Customer[];
  open: boolean;
  onClose: () => void;
}

export const MapDialog = ({ open, onClose, data }: MapDialogComponentProps) => {
  const handleCloseMapDialog = () => closeDialog();

  const closeDialog = () => {
    onClose();
  };

  const [openPin, setOpenPin] = useState(false);

  function createData(customers: Customer[]) {
    return (
      customers.map((record) => {
        return {
          key: record.id,
          firstName: record.firstName,
          lastName: record.lastName,
          company: record.company,
          city: record.city,
          street: record.street,
          phoneNumber: record.phoneNumber,
          emailAddress: record.emailAddress,
          lastOverviewDate: record.lastOverviewDate,
          lat: record.lat,
          lng: record.lng,
        };
      }) || []
    );
  }

  const pinCustomers = createData(data);

  console.log(pinCustomers);

  const Markers = ({ points }: Props) => {
    return (
      <>
        {points.map((point) => (
          <AdvancedMarker
            position={point}
            key={point.key}
            onClick={() => setOpenPin(true)}
          >
            <Pin />
            {openPin && (
              <InfoWindow
                position={point}
                onCloseClick={() => setOpenPin(false)}
              >
                <p>
                  {point.firstName},<br />
                  {point.lastName},<br />
                  {point.company}, {point.city},<br />
                  {point.street},<br />
                  tel.:{point.phoneNumber}, {point.emailAddress},<br />
                  data ostatniego przegladu: {point.lastOverviewDate}
                </p>
              </InfoWindow>
            )}
          </AdvancedMarker>
        ))}
      </>
    );
  };

  return (
    <Dialog
      fullWidth={true}
      open={open}
      onClose={handleCloseMapDialog}
      disableRestoreFocus
    >
      <DialogTitle>Zobacz klientow na mapie</DialogTitle>
      <DialogContent>
        <APIProvider apiKey="">
          <div style={{ height: "70vh", width: "100%" }}>
            <Map
              gestureHandling="greedy"
              minZoom={4}
              maxZoom={18}
              defaultZoom={9}
              defaultCenter={{ lat: 52.5, lng: 16.8 }}
              mapId=""
            >
              <Markers points={pinCustomers} />
            </Map>
          </div>
        </APIProvider>
      </DialogContent>
      ``
      <DialogActions>
        <Button onClick={handleCloseMapDialog}>Zamknij</Button>
      </DialogActions>
    </Dialog>
  );
};
